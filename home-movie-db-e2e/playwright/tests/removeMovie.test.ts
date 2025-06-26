import test, { expect } from "@playwright/test";

test.describe("Test remove movie", () => {
    test('remove movie from collection', async ({page}) => {
        await page.goto('/search');
        await page.getByPlaceholder('Search...').fill('Die Verurteilten');
        await page.getByRole('button', { name: 'Search' }).click();
        await page.waitForSelector('button')
        await page.getByRole('button', { name: 'Add Movie' }).first().click();
        await page.waitForSelector('button')
        await page.getByText('<- Zurück zur Startseite').click();
        await page.getByRole('button', { name: 'Details' }).click();
        await expect(page).toHaveURL(/.*\/movie\/\d+/);
        await expect(page.getByRole('button', { name: 'Entfernen' })).toBeVisible();
        await page.getByRole('button', { name: 'Entfernen' }).click();
        await page.waitForSelector('button')
        await expect(page.getByRole('button', { name: 'Hinzufügen' })).toBeVisible();
    })
});