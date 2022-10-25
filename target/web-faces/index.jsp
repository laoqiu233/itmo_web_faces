<?xml version='1.0' encoding='UTF-8'?>
<!DOCTYPE html>
<html xmlns="https://www.w3.org/1999/xhtml"
      xmlns:h="https://java.sun.com/jsf/html">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/style.css">
    <title>WEBLAB#2</title>
</head>
<body>
    <header>
        <h1><span class="theme">Tsiu</span> T.</h1>
        <h2>P32312 <span class="gray">Web Lab #2 | Вариант: 6663128</span></h2>
    </header>
    <div class='main'>
        <div class="panel" style="text-align: center;">
            <img src="/static/img/zxc-cursed.gif" id="loading-gif" width="300" height="300">
            <canvas id="graph-canvas" width="300" height="300"> 
                <img src="/static/img/graph.png"/>
            </canvas>
            <br/>
        </div>
        <div class="panel" id="form-panel">
            <form id="form" action="" method="get"></form>
        </div>
        <div id="results" class="panel">
            <h1>Results</h1>
            <div class="table-wrapper">
                <table>
                    <tbody>
                        <tr>
                            <th>Attempt #</th>
                            <th>X</th>
                            <th>Y</th>
                            <th>R</th>
                            <th>Result</th>
                            <th>Attempt time</th>
                            <th>Processing time</th>
                        </tr>
                        <tr>
                            <td>${i}</td>
                            <td>${attempt.point().x()}</td>
                            <td>${attempt.point().y()}</td>
                            <td>${attempt.point().r()}</td>
                            <td class="${attempt.success() ? 'theme' : 'warning'}">${ attempt.success() ? 'HIT' : 'MISS' }</td>
                            <td><%= new Date(((PointAttempt) pageContext.getAttribute("attempt")).attemptTime()) %></td>
                            <td>${attempt.processTime()} ms</td>
                            <c:set var="i" value="${i+1}"/>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>