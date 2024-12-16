# Projenin Amacı

Bu proje, hastane süreçlerini daha düzenli ve erişilebilir hale getirmeyi amaçlamaktadır. Hastaların randevularını kolayca alabilmesi, aktif randevularını ve kendilerine konulan tanıları görüntüleyebilmesi sağlanırken, doktorlar da hastalara tanı koyup bu bilgileri sistem üzerinden kaydedebilmektedir. Böylece hem hastalar hem de doktorlar için zaman kazandıran ve işleyişi kolaylaştıran bir sistem oluşturulmuştur. 

# Projede Kullanılan Teknolojiler

- **Programlama Dilleri:** Proje Java kullanılarak geliştirilmiştir. 
- **Kullanılan Kütüphaneler:** Swing, java.io, java.time
- **API:** JDBC MySQL Connector
- **Veritabanı:** MySQL
- **Diğer Araçlar:** Git

# Çalışma Prensibi


- Kullanıcıdan alınan veriler bir mysql server'ında depolanır.
- Mysql conncetor ile server arasında veri aktarımına izin verilir ve bu sayede sisteme veri akışı sağlanıp sistemden veriler alınabilir.
- Randevu bilgileri, kullanıcı, çalışan bilgileri gibi bilgiler mysql serverındaki uygun tablolarda saklanır ve gerektiğinde bilgiler buradan çekilir.
- Admin sisteme personel ekleme ve silme yetkisine sahiptir. Doktor randevularrını görüntüleyebilir ve hastalarına teşhis koyma yetkisi vardır.
- Müşteri(Hasta) ise sistemden randevu alabilmekte. Varolan randevularını görüntüleyebilmekte ve doktorların kendilerine koydukları teşhisleri görüntüleyebilmektedirler.
- Her kullanıcının genel hesap operasyonları vardır, bunlar: Hesaplarını silme, kullanıcı bilgilerini güncelleyebilme.

# Nasıl Çalışır


1. **Projeyi Kurma:** Projeyi doğrudan indirerek Proje dizininde bulunan HastaneOtomasyonu.jar dosyasını açarak uygulamaya erişebilirsiniz

- Açabilmek için Java yüklü olmak zorundadır.
- Eğer proje çalışmazsa projeye Mysql connector'ü modül olarak eklemeniz gerekmetedir.
 

2. **Modül Ekleme (IntelliJ Idea) :** Proje dosyasını derleyicide açın, daha sonra sol üstte bulunan File kısmından Project Scructure'ı seçin daha sonra sırasıyla Modules->Dependencies-> o kısımdaki + tuşuna basın JARs or Directories kısmını seçip Projemizin lib klasöründe bulunan dosyayı seçin. Daha sonra ise apply diyip OK tuşuna bastığınızda sisteme modül tanımlanmış olacaktır.
   

3. **Konfigürasyon:** src/resources/config.properties.example dosyasındaki db.url , db.user , db.password kısımlarını kendi sunucunuza bağlayın ve gerekli mysql tablolarınızı oluşturun.


4. **SQL Tabloları:** kod dosyasındaki SQL_tables.sql dosyasında gerekli tablo oluşturma kodları verilmiştir.
