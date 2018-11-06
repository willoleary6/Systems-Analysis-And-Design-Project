import json
import selectDiscountsByFlightID


def lambda_handler(event, context):
    results_tuple = selectDiscountsByFlightID.handler(event["flightID"])
    return {
        "statusCode": 200,
        "keys": json.dumps(results_tuple[0]),
        "results": json.dumps(results_tuple[1])
    }


