# Jak jsem udělal Users — vysvětlení pro začátečníky

Tento dokument vysvětluje krok po kroku, co jsme napsali a proč. Nepředpokládá žádné znalosti Javy ani objektového programování.

---

## Co je třída (class)?

Třída je šablona neboli vzor. Představ si ji jako formulář:

> Formulář "Uživatel" má políčka: jméno, e-mail, věk, ID.

V Javě to vypadá takto:

```java
public class Users {
    private Long id;
    private String name;
    private String email;
    private int age;
}
```

- `public` — třída je viditelná z celého projektu
- `class Users` — pojmenováváme šablonu "Users"
- `Long id` — políčko pro číslo (Long = velké celé číslo)
- `String name` — políčko pro text (String = řetězec znaků)
- `int age` — políčko pro věk (int = malé celé číslo)
- `private` — tato políčka jsou soukromá, nelze je měnit přímo zvenku

---

## Co jsou gettery a settery a proč je chceme?

Protože políčka jsou `private` (soukromá), nemůže je nikdo z venku přečíst ani změnit přímo. Proto vytvoříme "dveře" — metody, které to umožní řízeně.

**Getter** = metoda pro čtení hodnoty:

```java
public String getName() {
    return name;
}
```

- `getName()` — název metody, vždy začíná `get` + název políčka s velkým písmenem
- `return name` — vrátí aktuální hodnotu políčka `name`

**Setter** = metoda pro zapsání hodnoty:

```java
public void setName(String name) {
    this.name = name;
}
```

- `setName(String name)` — přijme novou hodnotu jako parametr
- `void` — tato metoda nic nevrací (jen uloží)
- `this.name = name` — `this.name` je políčko třídy, `name` je hodnota, co přišla zvenku

### Všechny gettery a settery ve třídě Users:

| Políčko | Getter        | Setter            |
|---------|---------------|-------------------|
| id      | `getId()`     | `setId(Long id)`  |
| name    | `getName()`   | `setName(String)` |
| email   | `getEmail()`  | `setEmail(String)`|
| age     | `getAge()`    | `setAge(int)`     |

---

## Co je endpoint?

Endpoint je adresa, na kterou může někdo poslat požadavek přes internet (nebo lokálně).

Například:
- `GET /hello` — přijde na adresu, dostane odpověď
- `POST /user/all` — pošle data na adresu, dostane odpověď

**Rozdíl mezi GET a POST:**
- `GET` — jen se ptám, nic neposílám (jako otevřít webovou stránku)
- `POST` — posílám data (jako vyplnit a odeslat formulář)

---

## Jak funguje náš endpoint POST /user/all?

```java
private final List<Users> userList = new ArrayList<>();

@PostMapping("/user/all")
public List<Users> addUser(@RequestBody Users user) {
    userList.add(user);
    return userList;
}
```

### Řádek po řádku:

**`private final List<Users> userList = new ArrayList<>();`**
- Vytvoříme prázdný seznam uživatelů v paměti aplikace
- `List<Users>` = seznam, který obsahuje objekty typu `Users`
- `new ArrayList<>()` = konkrétní prázdný seznam
- `final` = proměnná se nikdy nenahradí jiným seznamem (ale obsah se měnit může)

**`@PostMapping("/user/all")`**
- Říká Springu: "Tato metoda obsluhuje POST požadavky na adresu /user/all"

**`public List<Users> addUser(@RequestBody Users user)`**
- `@RequestBody Users user` = Spring automaticky převede JSON z požadavku na objekt `Users`
- Metoda vrátí `List<Users>` = celý seznam všech uživatelů

**`userList.add(user);`**
- Přidáme nového uživatele do seznamu

**`return userList;`**
- Vrátíme celý seznam — Spring ho automaticky převede na JSON

---

## Jak endpoint otestovat?

Pošli POST požadavek na `http://localhost:8080/user/all` s tímto tělem (body):

```json
{
  "id": 1,
  "name": "Jan Novák",
  "email": "jan@example.com",
  "age": 30
}
```

Odpověď bude seznam všech dosud přidaných uživatelů:

```json
[
  {
    "id": 1,
    "name": "Jan Novák",
    "email": "jan@example.com",
    "age": 30
  }
]
```

Pokud pošleš druhý požadavek s jiným uživatelem, seznam bude obsahovat oba.

> **Pozor:** Data jsou uložena pouze v paměti. Po restartu aplikace se smažou. Pro trvalé ukládání by bylo potřeba přidat databázi.

---

## Kde jsou soubory?

| Soubor | Co dělá |
|--------|---------|
| `src/main/java/com/example/demo/Users.java` | Třída Users — šablona uživatele s gettery a settery |
| `src/main/java/com/example/demo/DemoApplication.java` | Hlavní třída — obsahuje všechny endpointy včetně POST /user/all |
