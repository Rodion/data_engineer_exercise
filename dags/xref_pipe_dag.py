"""
Code that goes along with the Airflow tutorial located at:
https://github.com/apache/airflow/blob/master/airflow/example_dags/tutorial.py
"""
from builtins import range
from airflow.operators.bash_operator import BashOperator
from airflow.models import DAG, Variable
from datetime import datetime, timedelta
from croniter import croniter
import os

dag_id = 'xref_pipe'
schedule_interval = '* */1 * * *'
from_date = '11/01/2019'
default_args = {
    'owner': 'airflow',
    'depends_on_past': True,
    'retries': 1,
    'retry_delay': timedelta(seconds=60),
    'start_date': datetime.strptime('2019-05-20 11:30:00', "%Y-%m-%d %H:%M:%S"),
    'execution_timeout': timedelta(seconds=7200),
}

dag = DAG(dag_id, default_args=default_args, schedule_interval=schedule_interval)

query_executor = BashOperator(
    task_id='query_executor',
    bash_command='sh -c \'$EXERCISE_HOME/tasks/query_executor.sh 11/01/2019 $EXERCISE_HOME/output \'',
    dag=dag)

xref_report = BashOperator(
    task_id='xref_report',
    bash_command='sh -c \'$EXERCISE_HOME/tasks/xref_report.sh\'',
    retries=1,
    dag=dag)

xref_report.set_upstream(query_executor)
