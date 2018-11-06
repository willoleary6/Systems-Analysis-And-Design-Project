import json
import selectUserByID


def lambda_handler(event, context):
    results_tuple = selectUserByID.handler(event["id"])
    return {
        "statusCode": 200,
        "keys": json.dumps(results_tuple[0]),
        "results": json.dumps(results_tuple[1])
    }


