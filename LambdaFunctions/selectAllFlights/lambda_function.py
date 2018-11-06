import json
import selectAllFlights


def lambda_handler(event, context):
    results_tuple = selectAllFlights.handler()
    return {
        "statusCode": 200,
        "keys": json.dumps(results_tuple[0]),
        "results": json.dumps(results_tuple[1])
    }
