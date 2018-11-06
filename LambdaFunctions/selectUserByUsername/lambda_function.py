import json
import selectUserByUsername


def lambda_handler(event, context):
    results_tuple = selectUserByUsername.handler(event["username"])
    return {
        "statusCode": 200,
        "keys": json.dumps(results_tuple[0]),
        "results": json.dumps(results_tuple[1])
    }


