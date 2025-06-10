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
    await page.getByPlaceholder('Vorname').fill('Playwright');
    await page.getByPlaceholder('Nachname').fill('User');
    await page.getByPlaceholder('Username').fill('playwrightuser');
    await page.getByPlaceholder('Password').fill('password123');
    await page.getByRole('button', { name: 'Registrieren' }).click();
    // TODO: Add assertions to check for successful signup
  });

  test('should display error message for invalid signup', async ({ page }) => {
    await page.getByPlaceholder('Username').fill('testuser');
    await page.getByPlaceholder('Password').fill('password123');
    await page.getByRole('button', { name: 'Registrieren' }).click();
    await expect(page.getByText('Invalid email address')).toBeVisible();
  });

  test('redirect to login page', async ({ page }) => {
    await page.getByText('Zurück').click();
    await expect(page).toHaveURL('/');
    await expect(page.getByText('Willkommen in der Home Movie DB')).toBeVisible();
  });
});