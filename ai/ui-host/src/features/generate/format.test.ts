import { describe, expect, it } from 'vitest';
import { elementLabel, labelFor, seconds, timeAgo } from './format';

describe('format', () => {
  it('turns a snake_case attribute into a sentence-case label', () => {
    expect(labelFor('build_system')).toBe('Build system');
    expect(labelFor('unit_test')).toBe('Unit test');
  });

  it('leaves a single word capitalised', () => {
    expect(labelFor('language')).toBe('Language');
  });

  it('turns a PascalCase element name into plain lowercase words', () => {
    expect(elementLabel('SoftwareArchitecture')).toBe('software architecture');
  });

  it('leaves a single-word element name as one lowercase word', () => {
    expect(elementLabel('Pipeline')).toBe('pipeline');
  });

  it('formats milliseconds as seconds with one decimal', () => {
    expect(seconds(1234)).toBe('1.2 s');
    expect(seconds(0)).toBe('0.0 s');
  });

  describe('timeAgo', () => {
    const now = new Date('2026-03-10T12:00:00Z');
    const before = (seconds: number) => new Date(now.getTime() - seconds * 1000).toISOString();

    it('says "just now" for under a minute', () => {
      expect(timeAgo(before(0), now)).toBe('just now');
      expect(timeAgo(before(59), now)).toBe('just now');
    });

    it('uses the largest whole unit, singular and plural', () => {
      expect(timeAgo(before(60), now)).toBe('1 minute ago');
      expect(timeAgo(before(5 * 60), now)).toBe('5 minutes ago');
      expect(timeAgo(before(2 * 3600), now)).toBe('2 hours ago');
      expect(timeAgo(before(2 * 86400), now)).toBe('2 days ago');
      expect(timeAgo(before(8 * 86400), now)).toBe('1 week ago');
      expect(timeAgo(before(45 * 86400), now)).toBe('1 month ago');
      expect(timeAgo(before(400 * 86400), now)).toBe('1 year ago');
    });

    it('treats a timestamp slightly in the future as just now', () => {
      expect(timeAgo(before(-30), now)).toBe('just now');
    });

    it('shows nothing for a timestamp it cannot read', () => {
      expect(timeAgo('not a date', now)).toBe('');
    });
  });
});
