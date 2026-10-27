import { describe, it, expect } from 'vitest';
import { downloadName } from './download';

describe('downloadName', () => {
  it('drops the leading dot a browser would drop anyway', () => {
    expect(downloadName('.gitlab-ci.yml')).toBe('gitlab-ci.yml');
  });

  it('offers a file in a subfolder under its own name, since a download cannot be a folder', () => {
    expect(downloadName('.woodpecker/pipeline.yaml')).toBe('pipeline.yaml');
  });
});
