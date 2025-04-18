<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Каталог книг</title>
    <style>
        body {
            background-image: url('../image/background.jpg'); /* Фоновое изображение */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            color: #333;
        }

        .container {
            background-color: rgba(255, 255, 255, 0.95); /* Полупрозрачный белый фон */
            border-radius: 15px;
            padding: 30px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            width: 1200px; /* Фиксированная ширина */
            height: 650px; /* Фиксированная высота */
            display: flex;
            flex-direction: column;
            align-items: center;
            overflow-y: auto; /* Вертикальная прокрутка */
            position: relative; /* Для позиционирования кнопок */
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
            font-size: 18px;
        }

        td {
            font-size: 16px;
        }

        .actions {
            display: flex;
            gap: 10px;
        }

        .actions button {
            padding: 8px 15px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            background-color: #2196F3;
            color: white;
            font-size: 14px;
            transition: background-color 0.3s;
        }

        .actions button:hover {
            background-color: #1976D2;
        }

        .add-book-form {
            margin-top: 20px;
            width: 100%;
            display: flex;
            flex-wrap: wrap; /* Перенос элементов, если не помещаются */
            gap: 10px;
            align-items: center;
        }

        .add-book-form input, .add-book-form select, .add-book-form input[type="file"] {
            flex: 1;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
        }

        .add-book-form button {
            padding: 10px 20px;
            background-color: #2196F3;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .add-book-form button:hover {
            background-color: #1976D2;
        }

        /* Кнопка "На главную" */
        .home-button {
            position: absolute;
            top: 20px;
            left: 20px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .home-button:hover {
            background-color: #45a049;
        }

        /* Стили для выпадающего списка с чекбоксами */
        .custom-dropdown {
            position: relative;
            display: inline-block;
            width: 100%;
        }

        .dropdown-toggle {
            padding: 10px;
            background-color: #4CAF50; /* Зеленый цвет */
            border: 1px solid #45a049;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
            text-align: left;
            color: white;
            transition: background-color 0.3s;
        }

        .dropdown-toggle:hover {
            background-color: #45a049; /* Темно-зеленый цвет при наведении */
        }

        .dropdown-menu {
            display: none;
            position: absolute;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            width: 100%;
            z-index: 1;
            max-height: 150px;
            overflow-y: auto;
        }

        .dropdown-menu label {
            display: block;
            padding: 10px;
            cursor: pointer;
        }

        .dropdown-menu label:hover {
            background-color: #f2f2f2;
        }

        .dropdown-menu input[type="checkbox"] {
            margin-right: 10px;
        }

        .product-item img {
            max-width: 80px;
            max-height: 80px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<div class="container">
    <!-- Кнопка "На главную" -->
    <button class="home-button" onclick="location.href='/admin/main'">На главную</button>

    <h1>Каталог книг</h1>

    <!-- Таблица книг -->
    <table id="books-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Название</th>
            <th>Автор</th>
            <th>Цена</th>
            <th>Категории</th>
            <th>Количество</th>
            <th>Изображение</th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody id="books-table-body">
        <c:forEach var="book" items="${sessionScope.books.books}">
            <tr>

                <c:set var="categoryNames" value="" />
                <c:forEach var="category" items="${book.category}" varStatus="status">
                    <c:if test="${not status.first}">
                        <c:set var="categoryNames" value="${categoryNames}, " />
                    </c:if>
                    <c:set var="categoryNames" value="${categoryNames}${category.name}" />
                </c:forEach>

                <td>${book.id}</td>
                <td>${book.name}</td>
                <td>${book.writer}</td>
                <td>$${book.price}</td>
                <td>${categoryNames}</td>
                <td>${book.quantity}</td>
                <td>
                    <div class="book-item">
                        <img src="data:image/jpeg;base64,${book.image}" alt="${book.name}">
                    </div>
                </td>
                <td>
                    <button class="edit-btn" data-id="${book.id}">Редактировать</button>
                    <button class="delete-btn" data-id="${book.id}">Удалить</button>
                </td>
            </tr>

        </c:forEach>
        </tbody>
    </table>

    <!-- Форма для добавления books -->
    <form class="add-book-form" id="add-book-form" action="/admin/books" method="post" enctype="multipart/form-data">
        <input type="text" name="name" placeholder="Название книги" required>
        <label>
            <input type="text" name="writer" placeholder="Автор книги" required>
        </label>
        <input type="number" name="price" placeholder="Цена" step="0.01"  required>

        <!-- Выпадающий список с чекбоксами -->
        <div class="custom-dropdown">
            <button type="button" class="dropdown-toggle">Выберите категории</button>
<%--            <div class="dropdown-menu">--%>
<%--                <label><input type="checkbox" name="categories" value="Электроника"> Электроника</label>--%>
<%--                <label><input type="checkbox" name="categories" value="Одежда"> Одежда</label>--%>
<%--                <label><input type="checkbox" name="categories" value="Продукты"> Продукты</label>--%>
<%--                <label><input type="checkbox" name="categories" value="Книги"> Книги</label>--%>
<%--                <label><input type="checkbox" name="categories" value="Спорт"> Спорт</label>--%>
<%--                <label><input type="checkbox" name="categories" value="Фрукты"> Фрукты</label>--%>
<%--            </div>--%>
<%--        </div>--%>

        <input type="number" name="quantity" placeholder="Количество" required>
        <input type="file" name="image" accept="image/*">
        <button type="submit">Добавить книгу</button>
    </form>
</div>

<script>
    // Показать/скрыть выпадающий список
    document.querySelector('.dropdown-toggle').addEventListener('click', function () {
        const dropdownMenu = document.querySelector('.dropdown-menu');
        dropdownMenu.style.display = dropdownMenu.style.display === 'block' ? 'none' : 'block';
    });

    // Закрыть выпадающий список при клике вне его
    document.addEventListener('click', function (e) {
        if (!e.target.closest('.custom-dropdown')) {
            document.querySelector('.dropdown-menu').style.display = 'none';
        }
    });

    // Обновление текста на кнопке в зависимости от выбранных категорий
    document.querySelectorAll('.dropdown-menu input[type="checkbox"]').forEach(checkbox => {
        checkbox.addEventListener('change', function () {
            const selectedCategories = Array.from(document.querySelectorAll('.dropdown-menu input[type="checkbox"]:checked'))
                .map(checkbox => checkbox.value)
                .join(',');

            document.querySelector('.dropdown-toggle').textContent = selectedCategories || 'Выберите категории';
        });
    });

</script>

</body>
</html>