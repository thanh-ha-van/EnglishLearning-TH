<?php

function make_response($error_code, $message = '', $data = null) {
    return json_encode(
            array(
                'error_code' => $error_code,
                'message' => $message,
                'data' => $data
    ));
}
