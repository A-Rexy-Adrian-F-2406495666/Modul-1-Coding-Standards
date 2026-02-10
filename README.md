# Reflection 1: Penerapan Clean Code dan Secure Coding

Selama proses pengembangan aplikasi, saya menyadari pentingnya prinsip clean code dan secure coding untuk menjaga kualitas dan keamanan kode.

Dalam penerapan clean code, saya berusaha menulis kode yang jelas, konsisten, dan mudah dibaca. Misalnya:
- Penamaan variabel untuk masing-masing fitur create (`productId`), update (`updatedProductId`), dan delete (`deletedProductId`).
  
- Penamaan kelas dan method yang konsisten dan merepresentasikan fungsinya secara langsung seperti `editProductPost` dan `editProductPage` yang konsisten seperti pada fitur create, serta penamaan `findById` dan `deleteById` yang saya rasa cukup untuk menjelaskan fungsinya.

- Pengembangan fitur dengan branch terpisah, yaitu masing-masing fitur memiliki branch-nya tersendiri yang kemudian akan di-merge dengan main. Hal ini juga dapat memudahkan proses debugging.

Dalam penerapan secure coding, struktur projek saya mengikuti Model-View-Controller (MVC): model untuk data dan logika bisnis, view untuk menampilkan data, controller untuk handle input user. Dengan memanfaatkan struktur MVC, alur aplikasi menjadi lebih rapi dan jelas, serta meminimalkan celah risiko.

Kesulitan yang saya alami yaitu ketika mengimplementasikan fitur edit dan delete. Implementasi kodenya cukup membingungkan di awwal karena sedikit berbeda dengan fitur create di mana kedua fitur ini membutuhkan id yang sudah ada.
