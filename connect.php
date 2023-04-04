<?php

ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

    // Podaci iz prvog koraka - Licne informacije
    $broj_godina = $_POST['broj_godina'];
    $pol = isset($_POST['pol']) ? $_POST['pol'] : '';
    $godina_studija = $_POST['godina_studija'];
    $finansiranje = isset($_POST['finansiranje']) ? $_POST['finansiranje'] : '';
    $mesto_ucenja = isset($_POST['mesto_ucenja']) ? $_POST['mesto_ucenja'] : '';
    $organizacija = isset($_POST['organizacija']) ? $_POST['organizacija'] : '';

    // Podaci iz drugog koraka - Ispiti iz prve godine
    $m1 = $_POST['m1'];
    $ekonomija = $_POST['ekonomija'];
    $menadzment = $_POST['menadzment'];
    $oikt = $_POST['oikt'];
    $soc_psih = $_POST['soc_psih'];
    $sj1 = $_POST['sj1'];
    $m2 = $_POST['m2'];
    $p1 = $_POST['p1'];
    $osn_org = $_POST['osn_org'];
    $ps = $_POST['ps'];
    $uis = $_POST['uis'];

    // Podaci iz treceg koraka - Ispiti iz druge godine
    $aros = $_POST['aros'] ?? '';
    $p2 = $_POST['p2'] ?? '';
    $m3 = $_POST['m3'] ?? '';
    $marketing = $_POST['marketing'] ?? '';
    $tv = $_POST['tv'] ?? '';
    $sj2 = $_POST['sj2'] ?? '';
    $spa = $_POST['spa'] ?? '';
    $stat = $_POST['stat'] ?? '';
    $mtr = $_POST['mtr'] ?? '';
    $fmir = $_POST['fmir'] ?? '';
    $dms_num = $_POST['dms_num'] ?? '';

    // Podaci iz cetvrtog koraka - Ispiti iz trece godine
    $rmt = $_POST['rmt'] ?? '';
    $oi1 = $_POST['oi1'] ?? '';
    $ts = $_POST['ts'] ?? '';
    $epos = $_POST['epos'] ?? '';
    $mljr_up = $_POST['mljr_up'] ?? '';
    $oi2 = $_POST['oi2'] ?? '';
    $bp = $_POST['bp'] ?? '';
    $pj = $_POST['pj'] ?? '';
    $pois = $_POST['pois'] ?? '';
    $mpp = $_POST['mpp'] ?? '';
    $to_lins = $_POST['to_lins'] ?? '';

    // Podaci iz petog koraka - Ispiti iz cetvrte godine
    $proj_is = $_POST['proj_is'] ?? '';
    $int_tehn = $_POST['int_tehn'] ?? '';
    $simulacije = $_POST['simulacije'] ?? '';
    $proj_soft = $_POST['proj_soft'] ?? '';
    $izborni_isit1 = $_POST['izborni_isit1'] ?? '';
    $inteligentni_sistemi = $_POST['inteligentni_sistemi'] ?? '';
    $osn_kval = $_POST['osn_kval'] ?? '';
    $izborni_isit2 = $_POST['izborni_isit2'] ?? '';
    $izborni_isit3 = $_POST['izborni_isit3'] ?? '';
    $izborni_isit4 = $_POST['izborni_isit4'] ?? '';
    $strucna_praksa = $_POST['strucna_praksa'] ?? '';
    $zavrsni = $_POST['zavrsni'] ?? '';

    // Grupisanje ocena po godinama - pravimo nizove za svaku godinu
    $ocene1 = array($m1, $ekonomija, $menadzment, $oikt, $soc_psih, $sj1, $m2, $p1, $osn_org, $ps, $uis);
    $ocene2 = array($aros, $p2, $m3, $marketing, $tv, $sj2, $spa, $stat, $mtr, $fmir, $dms_num);
    $ocene3 = array($rmt, $oi1, $ts, $epos, $mljr_up, $oi2, $bp, $pj, $pois, $mpp, $to_lins);
    $ocene4 = array($proj_is, $int_tehn, $simulacije, $proj_soft, $izborni_isit1, $inteligentni_sistemi, $osn_kval, $izborni_isit2, $izborni_isit3, $izborni_isit4); //Bez strucne prakse i zavrsnog rada

    // Prolazenje kroz svaki niz; i ukoliko je student izabrao opciju "Nisam polozio", ocena postaje -1
    foreach ($ocene1 as &$ocena) {
        if ($ocena === "Nisam položio") {
            $ocena = -1;
        }
    }
    foreach ($ocene2 as &$ocena) {
        if ($ocena === "Nisam položio") {
            $ocena = -1;
        }
    }
    foreach ($ocene3 as &$ocena) {
        if ($ocena === "Nisam položio") {
            $ocena = -1;
        }
    }
    foreach ($ocene4 as &$ocena) {
        if ($ocena === "Nisam položio") {
            $ocena = -1;
        }
    }

    // Konekcija sa bazom podataka
    $conn = new mysqli('fdb1027.biz.nf', '4289994_anketa','dulesta10','4289994_anketa');
    if ($conn->connect_error) {
        die('Connection Failed : '.$conn->connect_error);
    } else {
        $stmt = $conn->prepare("insert into student(broj_godina, pol, godina_studija, finansiranje, mesto_ucenja, organizacija,
                                m1, ekonomija, menadzment, oikt, soc_psih, sj1, m2, p1, osn_org, ps, uis,
                                aros, p2, m3, marketing, tv, sj2, spa, stat, mtr, fmir, dms_num,
                                rmt, oi1, ts, epos, mljr_up, oi2, bp, pj, pois, mpp, to_lins,
                                proj_is, int_tehn, simulacije, proj_soft, izborni_isit1, inteligentni_sistemi, osn_kval, izborni_isit2, izborni_isit3, izborni_isit4, strucna_praksa, zavrsni)
                                values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        if($stmt){
            $stmt->bind_param("isssssiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiss", $broj_godina, $pol, $godina_studija, $finansiranje, $mesto_ucenja, $organizacija, 
                                                                                  $m1, $ekonomija, $menadzment, $oikt, $soc_psih, $sj1, $m2, $p1, $osn_org, $ps, $uis, 
                                                                                  $aros, $p2, $m3, $marketing, $tv, $sj2, $spa, $stat, $mtr, $fmir, $dms_num, 
                                                                                  $rmt, $oi1, $ts, $epos, $mljr_up, $oi2, $bp, $pj, $pois, $mpp, $to_lins, $proj_is, $int_tehn, 
                                                                                  $simulacije, $proj_soft, $izborni_isit1, $inteligentni_sistemi, $osn_kval, $izborni_isit2, $izborni_isit3, 
                                                                                  $izborni_isit4, $strucna_praksa, $zavrsni);
        $stmt->execute();
        if ($stmt->error) {
            echo "Error: " . $stmt->error;
        } else{
            echo "Uspešno unošenje! Možete zatvoriti ovaj prozor.";
        }
        $stmt->close();
    } else{
        echo "Došlo je do greške prilikom unošenja, molimo Vas pokušajte ponovo.";
    }
        $conn->close();
    }
?>