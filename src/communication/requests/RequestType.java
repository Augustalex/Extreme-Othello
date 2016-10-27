package communication.requests;

/**
 * Types of request that can be sent to another host/client.
 *
 * A question expects a response from the receiver in form of an answer,
 * often a boolean yes or no.
 *
 * An order prompts an action, if possible, at the other end. An order expects
 * a resource to be sent back from the receiving end. An order does not expect
 * error messages and a Question type request might be sent before hand to
 * check for possible errors.
 *
 * A demand prompts an action, if possible, at the other end. A demand does not
 * expect anything to be sent back from the receiving end.
 */
public enum RequestType {
    QEUSTION, ORDER, DEMAND;
}
