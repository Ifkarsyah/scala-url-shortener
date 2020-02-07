# My URL Shortener

My own implementation of URL shortener services like [TinyURL](https://tinyurl.com/) written in Scala.

## Example How to Use

Make POST request like this:
```curl
curl -X POST \
  http://localhost:9000/shorturl \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 2733f9b8-6f10-078c-0862-47d4e1c5247a' \
  -d '{
	"originalURL": "https://www.tablesgenerator.com/markdown_tables"
}'
```

**Response**:
```curl
{
    "shortURL": "localhost:9000/go/dO",
    "createdAt": "Fri, 7 Feb 2020 15:13:34 Western Indonesia Time",
    "expiredAt": "Tue, 11 Feb 2020 17:13:34 Western Indonesia Time"
}
```

