/**
 * This class holds the current values for the sent counters.
 * Counters are for all messages and messages with additional statuses.
 */
export class SentCounters {
  all: string;
  read: string;
  failed: string;
  delivered: string;
  none: string;
}
