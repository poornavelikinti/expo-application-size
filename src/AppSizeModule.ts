import { NativeModule,requireNativeModule } from 'expo';

export interface AppSizeResult {
  appBytes: number;
  dataBytes: number;
  cacheBytes: number;
}

declare class AppSizeModule extends NativeModule {
  /** Returns `{ appBytes, dataBytes, cacheBytes }` */
  getAppSize(): Promise<AppSizeResult>;
}

// Pull in the native module registered under AppSize
export default requireNativeModule<AppSizeModule>('AppSize');

