package routeCalculation;

public class EdgeDecorator implements Edge {
    protected Edge decoratedEdge;

    @Override
    public int getOrigin() {
        return decoratedEdge.getOrigin();
    }

    @Override
    public int getTarget() {
        return decoratedEdge.getTarget();
    }

    @Override
    public double getWeight() {
        return decoratedEdge.getWeight();
    }
}
