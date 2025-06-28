import { test, expect } from '@playwright/test';

test('test details page', async ({ page }) => { 
  await page.goto("/home")
  await page.getByRole('link', { name: 'Suche' }).click();
  await page.getByRole('textbox', { name: 'Search' }).click();
  await page.getByRole('textbox', { name: 'Search' }).fill('Fluch der Karibik');
  await page.getByRole('button', { name: 'Search' }).click();
  await page.getByRole('row', { name: 'Thumbnail Fluch der Karibik' }).getByRole('button').click();
  await page.getByRole('link', { name: '<- Zurück zur Startseite' }).click();
  await page.getByRole('link', { name: 'Details' }).click();
  await page.getByRole('button', { name: 'Entfernen' }).click();
  await expect(page.getByRole('button', { name: 'Hinzufügen' })).toBeVisible();
});