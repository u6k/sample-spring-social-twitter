# sample-spring-social-twitter

[![standard-readme compliant](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)
[![license](https://img.shields.io/github/license/u6k/sample-spring-social-twitter.svg)](https://github.com/u6k/sample-spring-social-twitter/blob/master/LICENSE)

> "Getting Started · Accessing Twitter Data"を素振りします。

__Table of Contents:__

<!-- TOC depthFrom:2 -->

- [Install](#install)
- [Usage](#usage)
- [Maintainer](#maintainer)
- [Contribute](#contribute)
- [License](#license)

<!-- /TOC -->

## Install

- Twitterにアプリケーションを登録します。
    - https://apps.twitter.com/
    - 以下の登録項目を設定します。
        - Name
        - Description
        - Website
        - Callback URL
            - 適当で良いです。Spring Socialによって、`/connect/twitterConnected`にリダイレクトされるため。
            - [Getting Started · Registering an Application with Twitter](https://spring.io/guides/gs/register-twitter-app/)ではCallback URLは空で良いように書いてありますが、Callback URLを登録しないとエラーになります。
- 環境変数を設定します。
    - `APP_TWITTER_CONSUMER_KEY`
        - Consumer Key
    - `APP_TWITTER_CONSUMER_SECRET`
        - Consumer Secret
- 起動します。
    - `./mvnw spring-boot:run`

## Usage

`http://localhost:8080`にアクセスすると、`/connect/twitterConnect`にリダイレクトされます。この画面に`Connect to Twitter`ボタンがあるので、これをクリックすることでTwitter認証を行います。認証後画面の`here`リンクをクリックすると、`/`にリダイレクトされ、TwitterのFriendのリストが表示されます。

## Maintainer

- [u6k - GitHub](https://github.com/u6k)

## Contribute

自由にforkしてください。

## License

[![license](https://img.shields.io/github/license/u6k/sample-spring-social-twitter.svg)](https://github.com/u6k/sample-spring-social-twitter/blob/master/LICENSE)
