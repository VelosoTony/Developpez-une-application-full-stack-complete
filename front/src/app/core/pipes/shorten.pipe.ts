import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'shorten',
})
export class ShortenPipe implements PipeTransform {
  transform(value: string, length: number): string {
    // If the value is shorter than or equal to the specified length, return it as is
    if (value.length <= length) {
      return value;
    }
    // If the value is longer than the specified length, truncate it and append '...'
    return value.substring(0, length) + '...';
  }
}
