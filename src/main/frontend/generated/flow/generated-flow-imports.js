import 'Frontend/generated/jar-resources/flow-component-renderer.js';
import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/combo-box/theme/lumo/vaadin-combo-box.js';
import 'Frontend/generated/jar-resources/comboBoxConnector.js';
import '@vaadin/tooltip/theme/lumo/vaadin-tooltip.js';
import '@vaadin/virtual-list/theme/lumo/vaadin-virtual-list.js';
import 'Frontend/generated/jar-resources/virtualListConnector.js';
import '@vaadin/button/theme/lumo/vaadin-button.js';
import '@vaadin/vertical-layout/theme/lumo/vaadin-vertical-layout.js';
import '@vaadin/horizontal-layout/theme/lumo/vaadin-horizontal-layout.js';
import 'Frontend/generated/jar-resources/disableOnClickFunctions.js';
import '@vaadin/multi-select-combo-box/theme/lumo/vaadin-multi-select-combo-box.js';
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
  if (key === 'a2ab0afce10750e2bc4d6b280cf3626a5bf7f69e52afdd8642a89162b489adf1') {
    pending.push(import('./chunks/chunk-44eb2bef1e2d57a4a4e695b9d4bd3845261c639db6c75311d189459d11bb257f.js'));
  }
  if (key === 'c0e9c8ca28824b2d903c8fc4f48b12a9e3dcfa469196540ca4379fa83a78e6fc') {
    pending.push(import('./chunks/chunk-0fd2643921eb3829159972d1ff01834d78a14dd6385c6c70081d5e9bed94bedd.js'));
  }
  if (key === 'c4b8749fd349ab4032079a57cab6ba22c10ab7f66c1dd6a4dccf2c576560c6e9') {
    pending.push(import('./chunks/chunk-0fd2643921eb3829159972d1ff01834d78a14dd6385c6c70081d5e9bed94bedd.js'));
  }
  if (key === '89809b9878a7a028206e285e08ea1864642de79328c7ea90dcd2b0774832066f') {
    pending.push(import('./chunks/chunk-3205b6eaacd69f58323afef2bd0481f3079f1904fc63601627b7163ebbab968a.js'));
  }
  if (key === 'dc098d74e6d214221aec7366b3a2a49c3642a80cc4aab80a1f85e752d505bb76') {
    pending.push(import('./chunks/chunk-3205b6eaacd69f58323afef2bd0481f3079f1904fc63601627b7163ebbab968a.js'));
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