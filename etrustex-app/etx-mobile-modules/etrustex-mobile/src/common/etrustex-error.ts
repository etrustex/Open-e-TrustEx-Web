/**
 * Error to be used by all services that do data manipulation, if needed. This error reports information about an error occurred when accessing the API.
 */
export class EtrustExError {

  constructor(public readonly type: string, public readonly details: string) {
  }

}
