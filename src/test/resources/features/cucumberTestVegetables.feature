#language: ru

Функция: Добавление товара типа 'Овощ' в список


Сценарий: Успешное добавление товара типа "Фрукт"

* открыт сайт "https://qualit.applineselenoid.fvds.ru/"
* перейти во вкладку "Песочница" и выбрать раздел "Товары"
* нажать на кнопку "Добавить"
* ввести название "Момордика" в поле ввода имени товара
* выбрать тип товара "Овощ"
* поставить галочку "Экзотический" если "нажать_на_чек_бокс" равно "Да"
* нажать на кнопку сохранить
* проверить что добавленный товар отображается в таблице под номером 5
* перейти во вкладку "Песочница" и выбрать раздел "Товары"
* нажать на кнопку "Добавить"
* ввести название "Картофель" в поле ввода имени товара
* выбрать тип товара "Овощ"
* нажать на кнопку сохранить
* проверить что добавленный товар отображается в таблице под номером 6
* перейти во вкладку "Песочница" и выбираю раздел "Сброс данных"
* закрыть страницу