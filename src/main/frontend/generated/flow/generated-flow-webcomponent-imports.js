import { injectGlobalWebcomponentCss } from 'Frontend/generated/jar-resources/theme-util.js';

import 'Frontend/generated/jar-resources/flow-component-renderer.js';
import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/combo-box/theme/lumo/vaadin-combo-box.js';
import 'Frontend/generated/jar-resources/comboBoxConnector.js';
import 'Frontend/generated/jar-resources/vaadin-grid-flow-selection-column.js';
import '@vaadin/grid/theme/lumo/vaadin-grid-column.js';
import '@vaadin/tooltip/theme/lumo/vaadin-tooltip.js';
import '@vaadin/virtual-list/theme/lumo/vaadin-virtual-list.js';
import 'Frontend/generated/jar-resources/virtualListConnector.js';
import '@vaadin/button/theme/lumo/vaadin-button.js';
import '@vaadin/vertical-layout/theme/lumo/vaadin-vertical-layout.js';
import '@vaadin/horizontal-layout/theme/lumo/vaadin-horizontal-layout.js';
import 'Frontend/generated/jar-resources/disableOnClickFunctions.js';
import '@vaadin/grid/theme/lumo/vaadin-grid-column-group.js';
import '@vaadin/context-menu/theme/lumo/vaadin-context-menu.js';
import 'Frontend/generated/jar-resources/contextMenuConnector.js';
import 'Frontend/generated/jar-resources/contextMenuTargetConnector.js';
import '@vaadin/multi-select-combo-box/theme/lumo/vaadin-multi-select-combo-box.js';
import '@vaadin/grid/theme/lumo/vaadin-grid.js';
import '@vaadin/grid/theme/lumo/vaadin-grid-sorter.js';
import '@vaadin/checkbox/theme/lumo/vaadin-checkbox.js';
import 'Frontend/generated/jar-resources/gridConnector.ts';
import '@vaadin/number-field/theme/lumo/vaadin-number-field.js';
import '@vaadin/text-field/theme/lumo/vaadin-text-field.js';
import 'Frontend/generated/jar-resources/lit-renderer.ts';
import '@vaadin/notification/theme/lumo/vaadin-notification.js';
import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';
import 'Frontend/generated/jar-resources/ReactRouterOutletElement.tsx';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === 'c0e9c8ca28824b2d903c8fc4f48b12a9e3dcfa469196540ca4379fa83a78e6fc') {
    pending.push(import('./chunks/chunk-0fd2643921eb3829159972d1ff01834d78a14dd6385c6c70081d5e9bed94bedd.js'));
  }
  if (key === 'dc098d74e6d214221aec7366b3a2a49c3642a80cc4aab80a1f85e752d505bb76') {
    pending.push(import('./chunks/chunk-3205b6eaacd69f58323afef2bd0481f3079f1904fc63601627b7163ebbab968a.js'));
  }
  if (key === 'c4b8749fd349ab4032079a57cab6ba22c10ab7f66c1dd6a4dccf2c576560c6e9') {
    pending.push(import('./chunks/chunk-0fd2643921eb3829159972d1ff01834d78a14dd6385c6c70081d5e9bed94bedd.js'));
  }
  if (key === '89809b9878a7a028206e285e08ea1864642de79328c7ea90dcd2b0774832066f') {
    pending.push(import('./chunks/chunk-3205b6eaacd69f58323afef2bd0481f3079f1904fc63601627b7163ebbab968a.js'));
  }
  if (key === 'a2ab0afce10750e2bc4d6b280cf3626a5bf7f69e52afdd8642a89162b489adf1') {
    pending.push(import('./chunks/chunk-d014269d9c69bedb72937cd6256bfc26a13656330b087ddd92e3d669e85a0139.js'));
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