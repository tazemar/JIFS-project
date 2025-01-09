import packageInfo from '../../package.json';

declare let window: any;

const ENV_VARIABLES: any = window.env;

export const environment = {
  productionMode: getEnvBooleanValue(ENV_VARIABLES.ENV_PRODUCTION_MODE),
};

function getEnvBooleanValue(envVar: any): boolean | null {
  if (envVar == null) {
    return null;
  }

  if (envVar === true || envVar === 'true' || envVar === 1 || envVar === '1') {
    return true;
  }

  if (
    envVar === false ||
    envVar === 'false' ||
    envVar === 0 ||
    envVar === '0'
  ) {
    return false;
  }

  return null;
}
