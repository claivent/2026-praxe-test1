# Jak začít s Angularem – průvodce od nuly

Tento dokument vysvětluje každý krok od instalace až po hotovou Angular aplikaci.
Cílová skupina: vývojáři, kteří znají HTML/CSS/JavaScript, ale Angular nikdy neviděli.

---

## 1. Co je Angular?

Angular je **framework** od Googlu pro stavbu webových aplikací v **TypeScriptu**.
Na rozdíl od čistého JavaScriptu (jako je ten v souboru `fe/index.html`) Angular:

- automaticky synchronizuje data s HTML (**data binding**)
- rozdělí aplikaci do **komponent** – znovupoužitelných bloků UI
- poskytuje **typovou bezpečnost** díky TypeScriptu
- výsledek zkompiluje do optimalizovaného JS bundle (jeden nebo více `.js` souborů)

---

## 2. Požadavky

```bash
# Ověř verzi Node.js – potřeba je 18+
node --version

# Ověř npm
npm --version
```

Pokud Node.js nemáš, stáhni ho z https://nodejs.org nebo použij **fnm** / **nvm**.

---

## 3. Instalace Angular CLI

Angular CLI (`ng`) je nástroj příkazové řádky pro tvorbu a správu Angular projektů.

```bash
npm install -g @angular/cli
```

Ověření:

```bash
ng version
```

---

## 4. Vytvoření nového projektu

```bash
# ng new <název-projektu> [přepínače]
ng new fe-angular --routing=false --style=scss --skip-git --skip-tests
```

| Přepínač | Co dělá |
|---|---|
| `--routing=false` | Nevytváří soubor pro routování (navigaci mezi stránkami) – pro jednoduchou SPA nepotřebujeme |
| `--style=scss` | Styly budou v SCSS (rozšíření CSS s proměnnými, vnořením atd.) |
| `--skip-git` | Nevytváří git repozitář (máme ho nadřazený) |
| `--skip-tests` | Nepřidává testovací soubory (pro demo nevyžadujeme) |

Příkaz stáhne všechny závislosti (`node_modules`) – může trvat 1–2 minuty.

---

## 5. Struktura projektu

Po vytvoření budeš mít:

```
fe-angular/
├── src/
│   ├── app/                   ← tady žije tvůj kód
│   │   ├── app.component.ts   ← hlavní komponenta (logika)
│   │   ├── app.component.html ← šablona (co se zobrazí)
│   │   ├── app.component.scss ← styly komponenty
│   │   └── app.config.ts      ← konfigurace aplikace (providery)
│   ├── index.html             ← vstupní HTML soubor
│   ├── main.ts                ← vstupní bod JS/TS
│   └── styles.scss            ← globální styly
├── angular.json               ← konfigurace Angular CLI
├── package.json               ← závislosti (npm)
└── tsconfig.json              ← konfigurace TypeScript kompilátoru
```

**Klíčový koncept:** Angular aplikace se skládá z **komponent**.
Každá komponenta = trojice `.ts` + `.html` + `.scss`.

---

## 6. Jak funguje komponenta

```typescript
// app.component.ts
import { Component, signal } from '@angular/core';

@Component({
  selector: 'app-root',       // HTML tag, který tato komponenta representuje
  standalone: true,           // Angular 17+: komponenta je soběstačná
  imports: [],                // ostatní komponenty/moduly, které tato používá
  templateUrl: './app.component.html',  // odkaz na HTML šablonu
  styleUrl: './app.component.scss'      // odkaz na styly
})
export class AppComponent {
  // Toto je TypeScript třída – logika komponenty
  title = 'Ahoj světe';

  // signal() = reaktivní proměnná (Angular 17+)
  // Když se změní hodnota, Angular automaticky překreslí HTML
  count = signal(0);

  increment() {
    this.count.update(n => n + 1);
  }
}
```

```html
<!-- app.component.html -->
<h1>{{ title }}</h1>           <!-- interpolace: zobrazí hodnotu title -->
<p>Počítadlo: {{ count() }}</p> <!-- signal se volá jako funkce () -->
<button (click)="increment()">Přidat</button>  <!-- event binding -->
```

**Základní syntaxe šablon:**

| Syntaxe | Účel | Příklad |
|---|---|---|
| `{{ hodnota }}` | Zobrazení hodnoty | `{{ user.name }}` |
| `[property]="výraz"` | Nastavení HTML atributu | `[disabled]="loading()"` |
| `(event)="metoda()"` | Naslouchání události | `(click)="save()"` |
| `@if (podmínka) {}` | Podmíněné zobrazení | `@if (users.length > 0) {}` |
| `@for (x of pole; track x.id) {}` | Cyklus | `@for (u of users(); track u.id) {}` |

---

## 7. Vytvoření service (logika pro API)

Service = třída pro sdílenou logiku (volání API, sdílený stav).
Není svázána s žádnou konkrétní komponentou.

```bash
# Vygenerování service přes CLI
ng generate service services/user
# zkratka: ng g s services/user
```

```typescript
// src/app/services/user.service.ts
import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })  // dostupná v celé aplikaci jako singleton
export class UserService {
  // inject() = moderní způsob dependency injection v Angular 14+
  private http = inject(HttpClient);

  getAll(): Observable<User[]> {
    return this.http.get<User[]>('/api/users');
  }
}
```

Aby `HttpClient` fungoval, musíš ho zaregistrovat v `app.config.ts`:

```typescript
// src/app/app.config.ts
import { ApplicationConfig } from '@angular/core';
import { provideHttpClient } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [provideHttpClient()]
};
```

---

## 8. Vytvoření subkomponenty

```bash
ng generate component components/add-user-sheet
# zkratka: ng g c components/add-user-sheet
```

Komponenty spolu komunikují přes **vstupy** (`input()`) a **výstupy** (`output()`):

```typescript
// add-user-sheet.component.ts
import { Component, output } from '@angular/core';

@Component({ selector: 'app-add-user-sheet', standalone: true, ... })
export class AddUserSheetComponent {
  // output() = událost, kterou komponenta vyšle rodičovi
  readonly saved  = output<void>();
  readonly closed = output<void>();

  submit() {
    // ... uložení dat
    this.saved.emit();   // oznámíme rodiči, že bylo uloženo
    this.closed.emit();  // oznámíme rodiči, aby zavřel sheet
  }
}
```

```html
<!-- app.component.html – použití subkomponenty -->
<app-add-user-sheet
  (saved)="fetchUsers()"    <!-- nasloucháme na output 'saved' -->
  (closed)="closeSheet()"   <!-- nasloucháme na output 'closed' -->
/>
```

---

## 9. Reaktivní formuláře (Reactive Forms)

Pro formuláře s validací použijeme `ReactiveFormsModule`:

```typescript
import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  standalone: true,
  imports: [ReactiveFormsModule],  // musíme importovat!
  ...
})
export class AddUserSheetComponent {
  private fb = inject(FormBuilder);

  form = this.fb.group({
    name:  ['', Validators.required],         // povinné pole
    email: ['', [Validators.required, Validators.email]], // více validátorů
    age:   [null as number | null]            // volitelné pole
  });

  submit() {
    this.form.markAllAsTouched(); // zobrazí chyby i pro nedotčená pole
    if (this.form.invalid) return;

    const { name, email, age } = this.form.value;
    // ... volání API
  }
}
```

```html
<form [formGroup]="form" (ngSubmit)="submit()">
  <input formControlName="name" />
  @if (form.get('name')?.touched && form.get('name')?.hasError('required')) {
    <span>Jméno je povinné</span>
  }
  <button type="submit">Uložit</button>
</form>
```

---

## 10. Signály (Signals) – Angular 17+

Signály jsou nový způsob správy reaktivního stavu:

```typescript
import { signal } from '@angular/core';

// Vytvoření signálu
const count = signal(0);

// Čtení hodnoty
console.log(count()); // 0

// Nastavení nové hodnoty
count.set(5);

// Aktualizace na základě předchozí hodnoty
count.update(n => n + 1);

// Derivovaný signál (computed)
import { computed } from '@angular/core';
const doubled = computed(() => count() * 2);
```

V šabloně se signál volá jako funkce: `{{ count() }}`, `[disabled]="loading()"`.

---

## 11. Spuštění vývojového serveru

```bash
cd fe-angular
npm start
# nebo
ng serve
```

Aplikace poběží na http://localhost:4200.
Vývojový server automaticky sleduje soubory – při každé změně se stránka obnoví.

---

## 12. Build pro produkci

```bash
npm run build
# nebo
ng build
```

Výstup je ve složce `dist/fe-angular/browser/`.
Soubory jsou minifikovány a optimalizovány pro nasazení.

---

## 13. Docker

Tento projekt obsahuje vícevrstvý `Dockerfile`:

```dockerfile
# 1. vrstva: build
FROM node:22-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci              # instalace závislostí (deterministická)
COPY . .
RUN npm run build       # kompilace Angular → statické soubory

# 2. vrstva: runtime (jen nginx + zkompilované soubory)
FROM nginx:alpine
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /app/dist/fe-angular/browser /usr/share/nginx/html
EXPOSE 80
```

```bash
# Sestavení Docker image
docker build -t fe-angular .

# Spuštění
docker run -p 3000:80 fe-angular
```

---

## 14. Souhrn struktury tohoto projektu

```
src/app/
├── models/
│   └── user.model.ts          ← TypeScript rozhraní (User, CreateUserDto)
├── services/
│   ├── user.service.ts        ← HTTP volání (/api/users)
│   └── toast.service.ts       ← sdílená logika toast notifikací
├── components/
│   └── add-user-sheet/        ← formulář pro přidání uživatele
│       ├── *.component.ts
│       ├── *.component.html
│       └── *.component.scss
├── app.component.ts           ← hlavní komponenta (seznam, delete, stav)
├── app.component.html         ← hlavní šablona
├── app.component.scss
└── app.config.ts              ← provideHttpClient()
```

---

## 15. Užitečné příkazy CLI

```bash
ng generate component components/moje-komponenta   # nová komponenta
ng generate service services/moje-service          # nová service
ng generate interface models/muj-model             # nové rozhraní
ng build --watch                                   # build + sledování změn
ng lint                                            # kontrola kódu
```

---

## Další zdroje

- Oficiální dokumentace: https://angular.dev
- Tutoriály krok za krokem: https://angular.dev/tutorials
- Angular DevTools (Chrome rozšíření pro ladění): https://angular.dev/tools/devtools
