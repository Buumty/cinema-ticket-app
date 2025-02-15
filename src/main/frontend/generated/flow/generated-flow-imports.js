import 'Frontend/generated/jar-resources/flow-component-renderer.js';
import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/virtual-list/src/vaadin-virtual-list.js';
import 'Frontend/generated/jar-resources/virtualListConnector.js';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import 'Frontend/generated/jar-resources/lit-renderer.ts';
import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/vaadin-lumo-styles/color-global.js';
import '@vaadin/vaadin-lumo-styles/typography-global.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';
import 'Frontend/generated/jar-resources/ReactRouterOutletElement.tsx';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === 'c4b8749fd349ab4032079a57cab6ba22c10ab7f66c1dd6a4dccf2c576560c6e9') {
    pending.push(import('./chunks/chunk-337291d846097fd468c3fe5317114fc8d530b082f1f1c0ce95523c10221effa9.js'));
  }
  if (key === '89809b9878a7a028206e285e08ea1864642de79328c7ea90dcd2b0774832066f') {
    pending.push(import('./chunks/chunk-1d8e9a738c2cab034099d38f5d491b5a214ed69347132a6e6bcfc85217a61767.js'));
  }
  if (key === 'dc098d74e6d214221aec7366b3a2a49c3642a80cc4aab80a1f85e752d505bb76') {
    pending.push(import('./chunks/chunk-1d8e9a738c2cab034099d38f5d491b5a214ed69347132a6e6bcfc85217a61767.js'));
  }
  if (key === 'c0e9c8ca28824b2d903c8fc4f48b12a9e3dcfa469196540ca4379fa83a78e6fc') {
    pending.push(import('./chunks/chunk-337291d846097fd468c3fe5317114fc8d530b082f1f1c0ce95523c10221effa9.js'));
  }
  return Promise.all(pending);
}

window.Vaadin = window.Vaadin || {};
window.Vaadin.Flow = window.Vaadin.Flow || {};
window.Vaadin.Flow.loadOnDemand = loadOnDemand;
window.Vaadin.Flow.resetFocus = () => {
 let ae=document.activeElement;
 while(ae&&ae.shadowRoot) ae = ae.shadowRoot.activeElement;
 return !ae || ae.blur() || ae.focus() || true;
}