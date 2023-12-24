# bitbudget

## !! Work in progress !!


The goal of this project is to be a Budget Control App, where you can add your incomes and expenses, define goals and manage your finances.

Since this is still a WIP, there is no support to run it locally without the AWS Secrets Credentials. But you can still remove the `AwsSecretsManager` and connect to a local Postgres database instead.

This application is going to be running on AWS Elastic Beanstalk, using AWS RDS {postgres} for the database and AWS Secrets Manager for the database credentials.


// TODO:
- [ ] Add tests
- [ ] Add CI/CD
- [ ] Add migrations
  - [ ] `CREATE EXTENSION IF NOT EXISTS citext;`
- [ ] Add authentication
- [ ] Add frontend