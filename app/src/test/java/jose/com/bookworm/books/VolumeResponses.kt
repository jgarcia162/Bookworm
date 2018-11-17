package jose.com.bookworm.books

import okhttp3.mockwebserver.MockResponse
import org.intellij.lang.annotations.Language

/** Mocked responses from [BooksApi] endpoint*/

@Language("JSON")
const val successfulFindVolumeJson =
    """{
 "kind": "books#volumes",
 "totalItems": 313,
 "items": [
  {
   "kind": "books#volume",
   "id": "UKteUQIR434C",
   "etag": "yNoo7TQPqcA",
   "selfLink": "https://www.googleapis.com/books/v1/volumes/UKteUQIR434C",
   "volumeInfo": {
    "title": "Vladimir Nabokov's Lolita",
    "subtitle": "A Casebook",
    "authors": [
     "Ellen Pifer"
    ],
    "publisher": "Oxford University Press on Demand",
    "publishedDate": "2003",
    "description": "Midway through the last century, Lolita burst on the literary scene--a Russian exile's extraordinary gift to American letters and the New World. The scandal provoked by the novel's subject--the sexual passion of a middle-aged European for a twelve-year-old American girl--was quickly upstaged by the critical attention it received from readers, scholars, and critics around the world. This casebook gathers together an interview with Nabokov as well as nine critical essays about Lolita. The essays follow a progression focusing first on textual and thematic features and then proceeding to broader topics and cultural implications, including the novel's relations to other works of literature and art and the movies adapted from it.",
    "industryIdentifiers": [
     {
      "type": "ISBN_10",
      "identifier": "0195150333"
     },
     {
      "type": "ISBN_13",
      "identifier": "9780195150339"
     }
    ],
    "readingModes": {
     "text": false,
     "image": true
    },
    "pageCount": 209,
    "printType": "BOOK",
    "categories": [
     "Literary Criticism"
    ],
    "averageRating": 5.0,
    "ratingsCount": 2,
    "maturityRating": "NOT_MATURE",
    "allowAnonLogging": false,
    "contentVersion": "preview-1.0.0",
    "imageLinks": {
     "smallThumbnail": "http://books.google.com/books/content?id=UKteUQIR434C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
     "thumbnail": "http://books.google.com/books/content?id=UKteUQIR434C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
    },
    "language": "en",
    "previewLink": "http://books.google.com/books?id=UKteUQIR434C&printsec=frontcover&dq=intitle:lolita&hl=&cd=1&source=gbs_api",
    "infoLink": "http://books.google.com/books?id=UKteUQIR434C&dq=intitle:lolita&hl=&source=gbs_api",
    "canonicalVolumeLink": "https://books.google.com/books/about/Vladimir_Nabokov_s_Lolita.html?hl=&id=UKteUQIR434C"
   },
   "saleInfo": {
    "country": "US",
    "saleability": "NOT_FOR_SALE",
    "isEbook": false
   },
   "accessInfo": {
    "country": "US",
    "viewability": "PARTIAL",
    "embeddable": true,
    "publicDomain": false,
    "textToSpeechPermission": "ALLOWED",
    "epub": {
     "isAvailable": false
    },
    "pdf": {
     "isAvailable": false
    },
    "webReaderLink": "http://play.google.com/books/reader?id=UKteUQIR434C&hl=&printsec=frontcover&source=gbs_api",
    "accessViewStatus": "SAMPLE",
    "quoteSharingAllowed": false
   },
   "searchInfo": {
    "textSnippet": "The essays follow a progression focusing first on textual and thematic features and then proceeding to broader topics and cultural implications, including the novel&#39;s relations to other works of literature and art and the movies adapted ..."
   }
  }
 ]
}"""

val successfulFindVolumeResponse: MockResponse =
        MockResponse()
            .setResponseCode(200)
            .addHeader("Content-Type: application/json")
            .addHeader("Content-Length: 3212")
            .setBody(successfulFindVolumeJson)