package jose.com.bookworm.books

import okhttp3.mockwebserver.MockResponse
import org.intellij.lang.annotations.Language

/** Mocked responses from [NYTimesApi] endpoints*/

@Language("JSON")
const val successfulGetBestSellersListJson =
    """{
  "status": "OK",
  "copyright": "Copyright (c) 2018 The New York Times Company.  All Rights Reserved.",
  "num_results": 10,
  "last_modified": "2017-03-21T13:38:01-04:00",
  "results": [
    {
      "list_name": "Travel",
      "display_name": "Travel",
      "bestsellers_date": "2016-12-31",
      "published_date": "2017-01-15",
      "rank": 1,
      "rank_last_week": 0,
      "weeks_on_list": 0,
      "asterisk": 0,
      "dagger": 0,
      "amazon_product_url": "http://www.amazon.com/Atlas-Obscura-Explorers-Worlds-Wonders/dp/0761169083?tag=NYTBS-20",
      "isbns": [
        {
          "isbn10": "0761169083",
          "isbn13": "9780761169086"
        }
      ],
      "book_details": [
        {
          "title": "ATLAS OBSCURA",
          "description": "A richly documented ultimate explorer's guide to more than 700 hidden marvels, events and curiosities around the world.",
          "contributor": "by Joshua Foer, Dylan Thuras and Ella Morton",
          "author": "Joshua Foer, Dylan Thuras and Ella Morton",
          "contributor_note": "",
          "price": 0,
          "age_group": "",
          "publisher": "Workman",
          "primary_isbn13": "9780761169086",
          "primary_isbn10": "0761169083"
        }
      ],
      "reviews": [
        {
          "book_review_link": "",
          "first_chapter_link": "",
          "sunday_review_link": ""
       }
      ]
    }
  ]
}"""

val successfulGetBestSellersListResponse: MockResponse =
    MockResponse()
        .setResponseCode(200)
        .addHeader("Content-Type: application/json")
        .addHeader("Content-Length: 1500")
        .setBody(successfulGetBestSellersListJson)

@Language("JSON")
const val successfulGetTopFiveBestSellersJson =
    """{
  "status": "OK",
  "copyright": "Copyright (c) 2018 The New York Times Company.  All Rights Reserved.",
  "num_results": 1,
  "results": {
    "bestsellers_date": "2018-11-10",
    "published_date": "2018-11-25",
    "published_date_description": "latest",
    "previous_published_date": "2018-11-18",
    "next_published_date": "",
    "overviewLists": [
      {
        "list_id": 704,
        "list_name": "Combined Print and E-Book Fiction",
        "list_name_encoded": "combined-print-and-e-book-fiction",
        "display_name": "Combined Print & E-Book Fiction",
        "updated": "WEEKLY",
        "list_image": "https://s1.nyt.com/du/books/images/9780399593512.jpg",
        "list_image_width": 320,
        "list_image_height": 495,
        "books": [
          {
            "age_group": "",
            "amazon_product_url": "https://www.amazon.com/Past-Tense-Jack-Reacher-Novel/dp/0399593519?tag=NYTBS-20",
            "article_chapter_link": "",
            "author": "Lee Child",
            "book_image": "https://s1.nyt.com/du/books/images/9780399593512.jpg",
            "book_image_width": 320,
            "book_image_height": 495,
            "book_review_link": "",
            "contributor": "by Lee Child",
            "contributor_note": "",
            "created_date": "2018-11-14 23:02:10",
            "description": "Jack Reacher explores the New England town where his father was born and a Canadian couple now find themselves stranded.",
            "first_chapter_link": "",
            "price": 0,
            "primary_isbn10": "",
            "primary_isbn13": "9781473542303",
            "publisher": "Delacorte",
            "rank": 1,
            "rank_last_week": 0,
            "sunday_review_link": "",
            "title": "PAST TENSE",
            "updated_date": "2018-11-14 23:02:10",
            "weeks_on_list": 1,
            "buy_links": [
              {
                "name": "Amazon",
                "url": "https://www.amazon.com/Past-Tense-Jack-Reacher-Novel/dp/0399593519?tag=NYTBS-20"
              },
              {
                "name": "Local Booksellers",
                "url": "http://www.indiebound.org/book/9781473542303?aff=NYT"
              },
              {
                "name": "Barnes and Noble",
                "url": "http://www.anrdoezrs.net/click-7990613-11819508?url=http%3A%2F%2Fwww.barnesandnoble.com%2Fw%2F%3Fean%3D9781473542303"
              }
            ]
          }
        ]
      }
    ]
  }
}"""

val successfulGetTopFiveBestSellersResponse: MockResponse =
    MockResponse()
        .setResponseCode(200)
        .addHeader("Content-Type: application/json")
        .addHeader("Content-Length: 1500")
        .setBody(successfulGetTopFiveBestSellersJson)

@Language("JSON")
const val successfulGetBestSellersListNamesJson =
    """{
  "status": "OK",
  "copyright": "Copyright (c) 2018 The New York Times Company.  All Rights Reserved.",
  "num_results": 55,
  "results": [
    {
      "list_name": "Combined Print and E-Book Fiction",
      "display_name": "Combined Print & E-Book Fiction",
      "list_name_encoded": "combined-print-and-e-book-fiction",
      "oldest_published_date": "2011-02-13",
      "newest_published_date": "2018-11-25",
      "updated": "WEEKLY"
    }
  ]
}"""

val successfulGetBestSellersListNamesResponse: MockResponse =
    MockResponse()
        .setResponseCode(200)
        .addHeader("Content-Type: application/json")
        .addHeader("Content-Length: 1500")
        .setBody(successfulGetBestSellersListNamesJson)




