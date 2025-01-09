module.exports = {
  root: true,
  env: {
    browser: true,
    es2021: true,
  },
  plugins: ['@typescript-eslint'],
  extends: [
    'eslint:recommended',
    'plugin:@typescript-eslint/recommended',
    'prettier',
  ],
  parser: '@typescript-eslint/parser',
  parserOptions: {
    ecmaVersion: 'latest',
    project: './tsconfig.json',
    tsconfigRootDir: __dirname,
  },
  ignorePatterns: ['.eslintrc.js', 'src/**/*.test.ts', 'src/**/*.spec.ts'],
  rules: {
    '@typescript-eslint/no-explicit-any': 'warn',
  },
};
