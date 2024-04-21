<h3>Прочти Меня</h3>

Это исходный код Lilith's Throne.

Вы должны согласиться с условиями прилагаемого отказа от ответственности и соблюдать условия лицензии, если хотите просмотреть этот исходный код.

Dev-note: Этот проект полагается на JavaFX, который не включен в Openjdk, что часто выбирают энтузиасты Linux. Если вы используете Linux, пожалуйста, убедитесь, что вы используете Oracle JDK для сборки этого проекта или установки OpenJFX.

Авторское право 2016 Innoxia (innoxia7@gmail.com) все права защищены.

Попытка сделать перевод на русский язык. В будущем возможно исправление написания полов. Часть текста находится в папке res а остальное в коде.
Текущая версия 0.4.9.9

<h3>Простая сборка вручную</h3>
<ol>
<li>Необходимо скачать Maven с оффициального сайта.</li>
<li>Распаковать архив в любую папку.</li>
<li>Скачать с оффициального сайта Oracle jdk-8u172. (У меня так не получилось из-за проблем с регистрацией. Любой другой вариант тоже подойдет.)</li>
<li>Создать под-папку в папке с Maven, в нее установить JDK 8-172.</li>
<li>Зайти по пути: apache-maven\bin, открыть файл mvn.cmd как текстовый документ.</li>
<li>После строки: "@REM ==== START VALIDATION ====" очистить все вплоть до: ":OkJHome", после чего вставить туда:
set JAVA_HOME=C:\apache-maven\JDK 8-172\ (Путь до папки с установленой JDK 8-172)<br>
if not "%JAVA_HOME%" == "" goto OkJHome <br>
Таким образом мы зададим эту версию JDK, как используемую Maven. (По умолчанию он использует системную самой последней версии).</li>
<li>Добавляем папку apache-maven\bin как переменную среды PATH. (После этого консоль обязательно нужно закрыть и открыть.)</li>
<li>Скачиваем код данного репозитория и распаковываем в любую папку.</li>
<li>С помощью "cd" переходим в эту папку и пишем "mvn package".</li>
<li>В папке с кодом появится: "target", внутри этой папки находится папка с игрой. (Там есть лишние файлы, они не важны.)</li>
<li>Для запуска нужна JRE 8-172. В качестве альтернативы предлагаю "боекомплект" с .bat и JRE в папке с игрой. (.bat запускает игру используя Java из этой папки.)</li>
</ol>
(Боекомплект и сами сборки выкладывать в релизы не могу и не буду по понятным причинам.)
