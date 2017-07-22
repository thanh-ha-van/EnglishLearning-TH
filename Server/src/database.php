<?php
class DB{
	public $db;
	
	public function __construct($db) {
        $this->db = $db;
    }

    public function getAllWords(){
    	$query = "SELECT * FROM `vocabulary` WHERE 1";
		$sth = $this->db->prepare($query);
		$sth->execute();
		return $sth->fetchAll();
    }

    public function getAllWordsAndMeans($language_code){
    	$query = "SELECT * FROM `vocabulary` v join means m on v.`id` = m.`id_word` WHERE `language_code` = '$language_code'";
		$sth = $this->db->prepare($query);
		$sth->execute();
		return $sth->fetchAll();
    }    

    public function getLanguagesEnable(){
    	$query = "SELECT * FROM `languages` WHERE enable = 1";
		$sth = $this->db->prepare($query);
		$sth->execute();
		return $sth->fetchAll();
    }

}