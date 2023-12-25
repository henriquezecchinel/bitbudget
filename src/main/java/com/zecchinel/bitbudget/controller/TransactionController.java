package com.zecchinel.bitbudget.controller;

import com.zecchinel.bitbudget.dto.*;
import com.zecchinel.bitbudget.model.*;
import com.zecchinel.bitbudget.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController extends BaseEntityController<Transaction, TransactionDto> {

    private final TransactionRepository transactionRepository;
    private final CurrencyRepository currencyRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final OwnerRepository ownerRepository;
    private final CountryRepository countryRepository;
    private final TagRepository tagRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public TransactionController(TransactionRepository transactionRepository, CurrencyRepository currencyRepository, SubCategoryRepository subCategoryRepository, OwnerRepository ownerRepository, CountryRepository countryRepository, TagRepository tagRepository, PaymentMethodRepository paymentMethodRepository) {
        this.transactionRepository = transactionRepository;
        this.currencyRepository = currencyRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.ownerRepository = ownerRepository;
        this.countryRepository = countryRepository;
        this.tagRepository = tagRepository;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    protected BaseEntityRepository<Transaction> getRepository() {
        return this.transactionRepository;
    }

    @Override
    protected TransactionDto convertToDto(Transaction entity) {
        MainCategoryDto mainCategoryDto = new MainCategoryDto(entity.getSubCategory().getMainCategory().getId(), entity.getSubCategory().getMainCategory().getDescription());
        CurrencyDto currencyDto = new CurrencyDto(entity.getCurrency().getId(), entity.getCurrency().getDescription());
        SubCategoryDto subCategoryDto = new SubCategoryDto(entity.getSubCategory().getId(), entity.getSubCategory().getDescription(), mainCategoryDto);
        OwnerDto ownerDto = new OwnerDto(entity.getOwner().getId(), entity.getOwner().getDescription());
        CountryDto countryDto = new CountryDto(entity.getCountry().getId(), entity.getCountry().getDescription());
        TagDto tagDto = new TagDto(entity.getTag().getId(), entity.getTag().getDescription());
        PaymentMethodDto paymentMethodDto = new PaymentMethodDto(entity.getPaymentMethod().getId(), entity.getPaymentMethod().getDescription());

        return TransactionDto.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .purchaseDate(entity.getPurchaseDate())
                .usageDate(entity.getUsageDate())
                .amount(entity.getAmount())
                .currency(currencyDto)
                .installments(entity.getInstallments())
                .monthsDuration(entity.getMonthsDuration())
                .subCategory(subCategoryDto)
                .owner(ownerDto)
                .country(countryDto)
                .tag(tagDto)
                .paymentMethod(paymentMethodDto)
                .build();
    }

    @Override
    public ResponseEntity<?> create(@RequestBody Transaction entity) {
        try {
            Currency currency = entity.getCurrency();
            SubCategory subCategory = entity.getSubCategory();
            Owner owner = entity.getOwner();
            Country country = entity.getCountry();
            Tag tag = entity.getTag();
            PaymentMethod paymentMethod = entity.getPaymentMethod();

            if (currency != null && subCategory != null && owner != null && country != null && tag != null && paymentMethod != null) {
                Optional<Currency> currencyDb = currencyRepository.findById(currency.getId());
                Optional<SubCategory> subCategoryDb = subCategoryRepository.findById(subCategory.getId());
                Optional<Owner> ownerDb = ownerRepository.findById(owner.getId());
                Optional<Country> countryDb = countryRepository.findById(country.getId());
                Optional<Tag> tagDb = tagRepository.findById(tag.getId());
                Optional<PaymentMethod> paymentMethodDb = paymentMethodRepository.findById(paymentMethod.getId());

                if (currencyDb.isPresent() && subCategoryDb.isPresent() && ownerDb.isPresent() && countryDb.isPresent() && tagDb.isPresent() && paymentMethodDb.isPresent()) {
                    Transaction transaction = Transaction.builder()
                            .id(entity.getId())
                            .description(entity.getDescription())
                            .purchaseDate(entity.getPurchaseDate())
                            .usageDate(entity.getUsageDate())
                            .amount(entity.getAmount())
                            .currency(currencyDb.get())
                            .installments(entity.getInstallments())
                            .monthsDuration(entity.getMonthsDuration())
                            .subCategory(subCategoryDb.get())
                            .owner(ownerDb.get())
                            .country(countryDb.get())
                            .tag(tagDb.get())
                            .paymentMethod(paymentMethodDb.get())
                            .build();

                    Transaction savedTransaction = transactionRepository.save(transaction);
                    return ResponseEntity.ok(convertToDto(savedTransaction));
                } else {
                    // TODO: Provide a distinct message for each entity
                    return ResponseEntity.badRequest().body("The ID of a provided entity does not exist");
                }
            } else {
                // TODO: Provide a distinct message for each entity
                return ResponseEntity.badRequest().body("A required field has not been provided");
            }
        } catch (Exception e) {
            // TODO: Improve error handling
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}