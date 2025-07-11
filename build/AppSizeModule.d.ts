import { NativeModule } from 'expo';
export interface AppSizeResult {
    appBytes: number;
    dataBytes: number;
    cacheBytes: number;
}
declare class AppSizeModule extends NativeModule {
    /** Returns `{ appBytes, dataBytes, cacheBytes }` */
    getAppSize(): Promise<AppSizeResult>;
}
declare const _default: AppSizeModule;
export default _default;
//# sourceMappingURL=AppSizeModule.d.ts.map