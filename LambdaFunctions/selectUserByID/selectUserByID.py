import sys
import logging
import rds_config
import pymysql

# rds settings
host = rds_config.rds_host
username = rds_config.db_username
password = rds_config.db_password
db_name = rds_config.db_name

logger = logging.getLogger()
logger.setLevel(logging.INFO)

try:
    connection = pymysql.connect(
        host,
        user=username,
        passwd=password,
        db=db_name,
        connect_timeout=100
    )
except:
    logger.error("ERROR: Unexpected error: Could not connect to MySql instance.")
    sys.exit()


def handler(user_id):
    with connection.cursor() as cur:
        # run query
        cur.execute(
            "SELECT * FROM users WHERE userID = " + user_id + " AND isActive = 1 LIMIT 0,1"
        )
        connection.close()
    return format_results(cur)


def format_results(cur):
    # retrieve the column names as keys
    keys = []
    for description in cur.description:
        keys.append(description[0])

    rows = cur.fetchall()
    results = []
    i = 0
    # store results as array of dictionaries
    for row in rows:
        j = 0
        results.append({})
        for key in keys:
            results[i][str(key)] = row[j]
            j += 1
        i += 1
    return keys, results
