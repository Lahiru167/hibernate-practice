# Hibernate Practice Repository

This repository contains hands-on examples and exercises to practice and understand Hibernate ORM (Object Relational Mapping) using Java.

---

## 🛠 Technologies Used

- Java 17+
- Hibernate ORM
- MySQL / H2 Database
- Maven or Gradle
- IntelliJ IDEA or Eclipse
- JDBC (for basic comparison)

---

## 📁 Project Structure


---

## 🧪 Topics Covered

- Hibernate Setup and Configuration
- Hibernate Annotations:
  - `@Entity`, `@Id`, `@Column`, `@Table`, etc.
- CRUD Operations:
  - Create, Read, Update, Delete
- Relationships:
  - One-to-One
  - One-to-Many
  - Many-to-Many
- HQL (Hibernate Query Language)
- Native SQL Queries
- Transactions and Sessions
- Lazy vs Eager Fetching
- Caching (First and Second Level)

---

## ⚙️ Hibernate Configuration (hibernate.cfg.xml)

```xml
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/hibernate_db</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">your_password</property>
        <property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>
        <property name="hibernate.hbm2ddl.auto">update</property>
        <property name="hibernate.show_sql">true</property>

        <!-- Entity Classes -->
        <mapping class="com.example.hibernate.entity.Student"/>
    </session-factory>
</hibernate-configuration>
