<?php



$app->get('/get_languages', function ($request, $response, $args) {
	$db = new DB($this->db);
	$languages = $db->getLanguagesEnable();
	$response->getBody()->write(make_response(0, "Load language success", $languages));
    return $response;
});

$app->get('/get_words', function ($request, $response, $args) {

    if(!isset($_GET["language_code"])){
		$response->getBody()->write(make_response(1, "Don't exists language_code", null));
		return $response;
	}
	$language_code = $_GET["language_code"];
	$db = new DB($this->db);
	$data = $db->getAllWordsAndMeans($language_code);
	$response->getBody()->write(make_response(0, "Load word success", $data));
    return $response;
});
