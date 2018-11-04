import json
import selectFlightsByDepartureAirport


def lambda_handler(event, context):
    results_tuple = selectFlightsByDepartureAirport.handler(event["airportID"])
    return {
        "statusCode": 200,
        "keys": json.dumps(results_tuple[0]),
        "results": json.dumps(results_tuple[1])
    }