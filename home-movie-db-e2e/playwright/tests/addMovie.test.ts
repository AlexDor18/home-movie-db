import test, { expect } from "@playwright/test";

test.describe("Test add new movie", () => {
    test.beforeEach(async ({ page }) => {
        await page.goto("/search");
    });

    test('should display empty search form', async ({page}) => {
        await expect(page.getByText('<- Zurück zur Startseite')).toBeVisible();
        await expect(page.getByPlaceholder('Search...')).toBeVisible();
        await expect(page.getByRole('button', { name: 'Search' })).toBeVisible();
        await expect(page.getByText('Geben Sie für die Suche einen Begriff in die Suchleiste ein')).toBeVisible();
        await page.screenshot({ path: 'addMovieInit.png' });
    })

    test('should display search results', async ({page}) => {
        await page.getByPlaceholder('Search...').fill('Star Wars');
        await page.getByRole('button', { name: 'Search' }).click();
        await page.waitForSelector('button')
        await page.screenshot({ path: 'searchMovie.png' });
        const addButtons = await page.$$('button');

        await expect(addButtons.length).toBeGreaterThan(0);
    })  

    test('add movie to watchlist', async ({page}) => {
        await page.getByPlaceholder('Search...').fill('Star Wars');
        await page.getByRole('button', { name: 'Search' }).click();
        await page.waitForSelector('button')
        await page.getByRole('button', { name: 'Add Movie' }).first().click();
        await page.waitForSelector('button')
        await expect(page.getByTestId('checkmark')).toBeVisible();
        await page.screenshot({ path: 'finishedAdd.png' });
    })
});