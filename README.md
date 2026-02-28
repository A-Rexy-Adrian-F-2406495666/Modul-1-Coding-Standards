# Modul 1: Coding Standards

## Reflection 1: Penerapan Clean Code dan Secure Coding

Selama proses pengembangan aplikasi, saya menyadari pentingnya prinsip clean code dan secure coding untuk menjaga kualitas dan keamanan kode.

Dalam penerapan clean code, saya berusaha menulis kode yang jelas, konsisten, dan mudah dibaca. Misalnya:
- Penamaan variabel untuk masing-masing fitur create (`productId`), update (`updatedProductId`), dan delete (`deletedProductId`).
  
- Penamaan kelas dan method yang konsisten dan merepresentasikan fungsinya secara langsung seperti `editProductPost` dan `editProductPage` yang konsisten seperti pada fitur create, serta penamaan `findById` dan `deleteById` yang saya rasa cukup untuk menjelaskan fungsinya.

- Pengembangan fitur dengan branch terpisah, yaitu masing-masing fitur memiliki branch-nya tersendiri yang kemudian akan di-merge dengan main. Hal ini juga dapat memudahkan proses debugging.

Dalam penerapan secure coding, struktur projek saya mengikuti Model-View-Controller (MVC): model untuk data dan logika bisnis, view untuk menampilkan data, controller untuk handle input user. Dengan memanfaatkan struktur MVC, alur aplikasi menjadi lebih rapi dan jelas, serta meminimalkan celah risiko.

Kesulitan yang saya alami yaitu ketika mengimplementasikan fitur edit dan delete. Implementasi kodenya cukup membingungkan di awwal karena sedikit berbeda dengan fitur create di mana kedua fitur ini membutuhkan id yang sudah ada.

# Modul 2: Continuous Integration, DevOps

## Reflection

### _Code Quality Issues_ yang Diperbaiki
Selama exercise, beberapa code quality issue yang saya perbaiki terdeteksi melalui Sonarcloud dengan pengecekan otomatis di GitHub.

Selama pengerjaan exercise, saya menemukan beberapa code quality issue seperti:
- Kurangnya validasi input
- Kurangnya coverage unit test
- Kesalahan konfigurasi antara Sonarcloud dan Jacoco
- Salah ketik pada fungsi yang mereturn ke suatu halaman template pada controller, yang ternyata case sensitive pada saat deployment, mengakibatkan error 500
  
Issue tersebut terdeteksi melalui Sonarcloud dan report test Jacoco

Strategi yang saya gunakan untuk memperbaikinya adalah:
- Menambah validasi input
- Menambah unit test untuk case yang belum tercoverage
- Memperbaiki konfigurasi Sonarcloud dan Jacoco pada `ci.yml` dan `build.gradle.kts` agar otomatis melakukan testing saat push ke Github
- Memperbaiki "typo" pada `ProductController` dan `ProductControllerTest` agar tidak menghasilkan error saat deployment serta tidak merusak test yang sudah ada

Saya memastikan setiap perbaikan tetap aman dengan menjalankan test secara berkala untuk menghindari regression.

### Evaluasi CI/CD yang Diimplementasikan

Workflow CI/CD yang saya buat di GitHub Actions sudah memenuhi konsep Continuous Integration karena setiap push atau pull request secara otomatis menjalankan build, testing, dan analisis kualitas kode. Proses ini membantu mendeteksi error lebih awal sebelum kode di-merge ke branch utama.

Selain itu, pipeline juga melakukan deployment otomatis ke platform PaaS setelah seluruh tahap berhasil, sehingga sudah mencerminkan Continuous Deployment. Proses ini membuat setiap perubahan yang lolos test langsung tersedia dalam versi ter-_deploy_ tanpa gangguan. Secara keseluruhan, implementasi ini membantu saya dalam menjaga kualitas kode dan mempercepat proses integrasi serta deployment. Tentunya masih ada ruang untuk melakukan peningkatan.
