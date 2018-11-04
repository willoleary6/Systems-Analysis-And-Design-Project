import json
import selectUserByUsernameAndPassword


def lambda_handler(event, context):
    results_tuple = selectUserByUsernameAndPassword.handler(event["username"], event["password"])
    return {
        "statusCode": 200,
        "keys": json.dumps(results_tuple[0]),
        "results": json.dumps(results_tuple[1])
    }

