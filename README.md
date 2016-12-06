# gnome

Teller for personal finances

## Dependencies

*   `gnome` uses [PLAID](https://plaid.com) APIs to get the transaction data.
    You need to create a developer account on PLAID to make use of gnome.
*   [clj-http](https://github.com/dakrone/clj-http)
*   [org.clojure/data.json](https://github.com/clojure/data.json)
*   [environ](https://github.com/weavejester/environ)

## setup

### credentials.json

The project assumes the presence of file `credentials.json` under `gnome/resources/config` folder.
For security purposes, this file is not included in the project. You have to create your own credentials.json file
with appropriate values. The format for the json file should be as follows:

```json
{
    "plaid-client-id": "",
      "plaid-public-key": "",
      "plaid-client-secret": "",
      "{{institution_code}}-access-token": "",
      "accounts": {
          "{{institution_code}}": "{{account_id_by_plaid}}"
      }
}
```

## License

Copyright © 2016 saratsista

Distributed under the MIT License.
