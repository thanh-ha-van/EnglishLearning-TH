<?php
return [
    'settings' => [
        'displayErrorDetails' => true, // set to false in production
        'addContentLengthHeader' => false, // Allow the web server to send the content-length header

        // Renderer settings
        'renderer' => [
            'template_path' => __DIR__ . '/../templates/',
        ],

        // Monolog settings
        'logger' => [
            'name' => 'slim-app',
            'path' => __DIR__ . '/../logs/app.log',
            'level' => \Monolog\Logger::DEBUG,
        ],
        
        //localhost
       //  "db" => [
//             "host" => "localhost",
//             "dbname" => "hot8",
//             "user" => "root",
//             "pass" => ""
//         ],
        //server
        "db" => [
            "host" => "localhost",
            "dbname" => "hot8",
            "user" => "magikdb",
            "pass" => "Magik@vn2015"
        ],
    ],
];
