# SI_2025_lab2_236030
## Христина Соломанова, 236030

![CFG](https://github.com/user-attachments/assets/b09885ca-c99b-408f-9fd9-d614fea0a572)
---
### Цикломатска комплексност

Цикломатската комплексност е `P + 1`, каде `P` е бројот на **предикатни јазли** во контролниот граф.

Во овој случај:
- Број на предикатни јазли: `P = 8`
- Цикломатската комплексност е `8 + 1 = 9`

Цикломатската комплексност може исто така да се определи и преку бројот на **региони** во графот.  
Графот има точно **9 региони**, што потврдува дека `цикломатската комплексност е еднаква на 9`.

### Тест случаи според _Every Statement_ критериумот

- Тест случај 1: `[allItems = null, cardNumber = "anything"]`  

- Тест случај 2: `[allItems = {Item(name = null, quantity = 1, price = 1, discount = 1)}, cardNumber = "anything"]`  

- Тест случај 3: `[allItems = {Item(name = "Computer", quantity = 1, price = 1, discount = 1)}, cardNumber = null]`  

- Тест случај 4: `[allItems={Item(name = "Computer", quantity = 1, price = 301, discount = 0)}, cardNumber = "ABCDEFGHIJKLMNOP"]`  

- Тест случај 5: `[allItems={Item(name = "Computer", quantity = 1, price = 301, discount = 0)}, cardNumber = "1234123412341234"]`  


#### ➤ Извршувањето на линии во кодот според тест случаи

| Тест Случај | Опис                                                                                  | Извршени линии |
|:-------------:|-----------------------------------------------------------------------------------------|-----------------|
|     **ТС1**      | `allItems = null`                                                                       | 51, 52, 53, 92   |
|     **ТС2**      | `Item.name = null`                                                                      | 51–52, 56, 58.1–58.2, 59, 60–61, 92 |
|     **ТС3**      | `discount > 0`, `cardNumber = null`                                                     | 51–52, 56, 58.1–58.2, 59, 60, 64–65, 68–69, 75, 58.3, 76, 86–87, 92 |
|     **ТС4**      | `discount = 0`, `price > 300`, `cardNumber = "ABCDEFGHIJKLMNOP"` (букви)                | 51–52, 56, 58.1–58.2, 59, 60, 64–65, 68, 71–72, 75, 58.3, 76, 77–78, 79.1–79.2, 80, 81, 82, 92 |
|     **ТС5**      | `discount = 0`, `price > 300`, `cardNumber = "1234123412341234"` (цифри)                | 51–52, 56, 58.1–58.2, 59, 60, 64–65, 68, 71–72, 75, 58.3, 76, 77–78, 79.1–79.2, 80, 81, 84, 79.3, 90, 92 |


  Заклучок: Потребни се минимум `5` тест случаи за да се **задоволи** _Every Statement_ критериумот

### Тест случаи според Multiple Condition критериумот за условот 
```java
if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10)
```
Го користам фактот што Java се служи со _**lazy evaluation**_.   
Конкретно во случајов, ако се одреди дека првиот или вториот услов е вистинит (`true`), тогаш останатите услови не се евалуираат.

| `item.getPrice() > 300` | `item.getDiscount() > 0` | `item.getQuantity() > 10` | Реден број на тест пример |
|:-----------------------:|:------------------------:|:-------------------------:|:-------------------------:|
|         T           |           X              |                X              |            1              |
|         F           |           T              |                X              |            2              |
|         F           |           F              |                T              |            3              |
|         F           |           F              |                F              |            4              |


Потребни се минимум `4` тест случаи за да се задоволи _Multiple Condition_ критериумот и тие се следните:
```java
[allItems = {Item(name = "Computer", quantity = 1, price = 301, discount = 1)}, cardNumber = null]   //  TC 1, T X X
[allItems = {Item(name = "Computer", quantity = 1, price = 1, discount = 1)}, cardNumber = null]    //   TC 2, F T X
[allItems = {Item(name = "Computer", quantity = 12, price = 1, discount = 0)}, cardNumber = null]  //    TC 3, F F T
[allItems = {Item(name = "Computer", quantity = 1, price = 1, discount = 0)}, cardNumber = null]  //     TC 4, F F F
```
---


