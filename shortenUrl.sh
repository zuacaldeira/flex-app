#!/bin/bash
URL_TO_SHORTEN=$1
curl -H "public-api-token: 30ba1851f923717828ec3a1a88e7dfe6" -X PUT -d "urlToShorten=$1" https://api.shorte.st/v1/data/url >> $2
echo '' >> $2