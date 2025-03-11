# Case Study

Bu proje, **Java, Selenium ve Cucumber BDD** kullanılarak geliştirilmiş bir test otomasyon framework'üdür. **Runner sınıfı** çalıştırıldığında test senaryoları çalıştırılır ve **Extent Reports** ile detaylı test raporları üretilir.

## 📌 Teknolojiler
- Java
- Selenium WebDriver
- Cucumber BDD
- JUnit
- Maven
- Extent Reports

---

## 🚀 Kurulum

Projeyi kullanmaya başlamak için aşağıdaki adımları takip edebilirsiniz:

### 1️⃣ Depoyu Klonlayın
```sh
  git clone <repo-link>
  cd caseStudy
```

### 2️⃣ Maven Bağımlılıklarını Yükleyin
```sh
  mvn clean install
```

### 3️⃣ Testleri Çalıştırın
Testleri çalıştırmak için `Runner` sınıfını kullanabilirsiniz:

#### IntelliJ IDEA'dan Çalıştırma
- `src/test/java/runners/Runner.java` dosyasını sağ tıklayıp **Run** seçeneğini seçin.

#### Komut Satırından Çalıştırma
```sh
  mvn test
```

---

## 📝 Yapı
Proje temel olarak şu klasör yapısına sahiptir:
```
caseStudy/
│── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/
│   │   │   ├── pages/
│   │   │   ├── utils/
│   │   │   ├── resources/
│   ├── test/
│   │   ├── java/
│   │   │   ├── hooks/
│   │   │   ├── runners/
│   │   │   ├── stepdefinitions/
│   │   ├── resources/
│   │   │   ├── features/
│   │   │   ├── cucumber.properties
│   │   │   ├── extent.properties
│   │   │   ├── extent-config.xml
│── pom.xml
```

- **`pages/`**: Sayfa nesneleri (Page Object Model)
- **`stepdefinitions/`**: Test adımları
- **`runners/`**: Cucumber Runner sınıfı
- **`features/`**: Test senaryoları (Gherkin syntax)
- **`resources/`**: Test yapılandırma dosyaları

---