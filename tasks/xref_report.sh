#!/usr/bin/env bash

#curl --url 'smtps://smtp.gmail.com:465' --ssl-reqd --mail-from 'rmolchadsky@gmail.com' --mail-rcpt 'rmolchadsky@gmail.com' --upload-file $EXERCISE_HOME/output/xref.csv  --insecure
curl --url 'smtps://smtp.gmail.com:465' --ssl-reqd --mail-from 'coinc866@gmail.com' --mail-rcpt 'rmolchadsky@gmail.com' --upload-file /usr/local/airflow/data_engineer_exercise/xref.csv --user 'coinc866@gmail.com:1z2x3c4v$V' --insecure
