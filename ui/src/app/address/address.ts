import { AddressType } from './addresstype';

export class Address {
    constructor(
      public name: string,
      public mobile: number,
      public postalcode: number,
      public city: string,
      public state: string,
      public address?: string,
      public landMark?: string,
      public alternateMobile?: number,

      public addressType?: AddressType
    ) {}
  }