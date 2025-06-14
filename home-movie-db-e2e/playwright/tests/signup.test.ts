import test, { expect } from '@playwright/test';

test.describe('Signup page', () => {
  test.beforeEach(async ({ page }) => {
    await page.goto('/signup');
  });

  test('should display signup form', async ({ page }) => {
    await expect(page.getByPlaceholder('Vorname')).toBeVisible();
    await expect(page.getByPlaceholder('Nachname')).toBeVisible();
    await expect(page.getByPlaceholder('Username')).toBeVisible();
    await expect(page.getByPlaceholder('Password')).toBeVisible();
    await expect(page.getByRole('button', { name: 'Registrieren' })).toBeVisible();
    await expect(page.getByText('Zurück')).toBeVisible();
  });

  test('should allow user to submit signup form', async ({ page }) => {
    const uniqueUserName = "playwrightuser" + Math.random(); 
    await page.getByPlaceholder('Vorname').fill('Playwright2');
    await page.getByPlaceholder('Nachname').fill('User');
    await page.getByPlaceholder('Username').fill(uniqueUserName);
    await page.getByPlaceholder('Password').fill('password123');
    await page.getByRole('button', { name: 'Registrieren' }).click();
    await expect(page).toHaveURL('/');
  });

  test('should display error message for invalid signup', async ({ page }) => {
    await page.getByRole('button', { name: 'Registrieren' }).click();
    await expect(page.getByText('Vorname ist erforderlich')).toBeVisible();
    await expect(page.getByText('Nachname ist erforderlich')).toBeVisible();
    await expect(page.getByText('Username ist erforderlich')).toBeVisible();
    await expect(page.getByText('Password ist erforderlich')).toBeVisible();
  });

  test('reject doublicated user', async ({ page }) => {
    await page.getByPlaceholder('Vorname').fill('Playwright');
    await page.getByPlaceholder('Nachname').fill('User');
    await page.getByPlaceholder('Username').fill('playwrightuser');
    await page.getByPlaceholder('Password').fill('password123');
    await page.getByRole('button', { name: 'Registrieren' }).click();
    await expect(page.getByText('Fehler beim Registrieren. Bitte nutzen Sie einen anderen Benutzernamen.')).toBeVisible();
  })

  test('redirect to login page', async ({ page }) => {
    await page.getByText('Zurück').click();
    await expect(page).toHaveURL('/');
    await expect(page.getByText('Willkommen in der Home Movie DB')).toBeVisible();
  });
});