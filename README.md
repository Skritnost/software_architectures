Проектирование архитектур программного обеспечения
1 Предметная область:
База данных магазина дисков
2 Предназначена для:
Оптовая и розничная продажа дисков
3 Пользователями являются:
•	Покупатель- может просматривать данные о дисках, а также добавлять диски в корзину.
•	Программист - может управлять данными (добавлять диск, изменять данные, искать диск по определённому критерию), однако для этого ему необходимо подключиться к серверу.
4 Бизнес процессы:
1.	Добавление диска в базу - посылается запрос о добавлении диска в базу данных
2.	Информация об издательстве - после обработки запроса, посылается новый запрос на Server о взятии нужной информации об издательстве, так как при добавлении диска пользователь выбирает издательство данного диска.
3.	Информация об авторе – операция аналогична запросу об издательстве.
Функциональные требования
1.	Программист
•	Добавить диск
•	Изменить данные
•	Искать диск по определенному критерию
2.	Покупатель
•	Просмотр информации о дисках
•	Поиск диска по определенному критерию
•	Добавление в корзину
Варианты использования, диаграмма прецедентов
 
Варианты использования клиентом
1.	Авторизация
2.	Покупка дисков
•	Добавление в корзину
•	Оплата корзины
•	Поиск диска по определенному критерию
Варианты использования программистом
1.	Авторизация
2.	Добавление данных
•	Добавить данные о диске
•	Добавить данные о категории
•	Добавить данные об издательстве
3.	Изменение данных
4.	Удаление данных
•	Удаление данные о диске
•	Удаление данные о категории
•	Удаление данные об издательстве
5.	Поиск диска по определенному критерию

Диаграмма классов
 
